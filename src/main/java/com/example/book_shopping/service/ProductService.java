package com.example.book_shopping.service;

import com.example.book_shopping.entity.Category;
import com.example.book_shopping.entity.Language;
import com.example.book_shopping.entity.Procedure;
import com.example.book_shopping.entity.Product;
import com.example.book_shopping.exception.BadRequestException;
import com.example.book_shopping.exception.NotFoundException;
import com.example.book_shopping.repository.CategoryRepository;
import com.example.book_shopping.repository.LanguageRepository;
import com.example.book_shopping.repository.ProcedureRepository;
import com.example.book_shopping.repository.ProductRepository;
import com.example.book_shopping.request.CreateProductRequest;
import com.example.book_shopping.request.UpdateProductRequest;
import com.example.book_shopping.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.*;

/**
 * @author lengo
 * created on 3/19/2022
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProcedureRepository procedureRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LanguageRepository languageRepository;

    public List<ProductResponse> getAllByCategoryId(int categoryId) {
        try {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isPresent()) {
                List<Product> products = productRepository.findAllByCategory(category.get());
                if (products != null) return toProductResponses(products);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<ProductResponse> getAllByProcedureId(int procedureId) {
        try {
            Optional<Procedure> procedure = procedureRepository.findById(procedureId);
            if (procedure.isPresent()) {
                List<Product> products = productRepository.findAllByProcedure(procedure.get());
                if (products != null) return toProductResponses(products);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<ProductResponse> getAllByLanguageId(int languageId) {
        try {
            Optional<Language> language = languageRepository.findById(languageId);
            if (language.isPresent()) {
                List<Product> products = productRepository.findAllByLanguage(language.get());
                if (products != null) return toProductResponses(products);
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<ProductResponse> getAllByPrice(double greater, double less) {
        try {
            List<Product> products = productRepository.findAllByPriceGreaterThanAndPriceLessThan(greater, less);
            if (products == null) throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
            return toProductResponses(products);

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<ProductResponse> search(String keyword) {
        try {
            Set<Product> products = new HashSet<>();
            products.addAll(productRepository.searchProductRelative(keyword));
            products.addAll(productRepository.searchProductMatch(keyword));
            if (!products.isEmpty()) return toProductResponses(List.copyOf(products));
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<ProductResponse> getAll() {
        try {
            return toProductResponses(productRepository.findAll());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public ProductResponse get(int id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                return toProductResponse(product.get());
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public ProductResponse add(CreateProductRequest request) {
        try {
            Optional<Category> category = categoryRepository.findById(request.getCategoryId());
            Optional<Procedure> procedure = procedureRepository.findById(request.getProcedureId());
            Optional<Language> language = languageRepository.findById(request.getLanguageId());
            if (procedure.isPresent() && category.isPresent() && language.isPresent()) {
                Product product = new Product();
                product.setAmount(request.getAmount());
                product.setCategory(category.get());
                product.setPrice(request.getPrice());
                product.setName(request.getName());
                product.setProcedure(procedure.get());
                product.setPublishingYear(request.getPublishingYear());
                product.setDescription(request.getDescription());
                product.setLanguage(language.get());
                return toProductResponse(productRepository.save(product));
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public ProductResponse update(int id, UpdateProductRequest request) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                Product data = product.get();
                if (request.getCategoryId() != 0) {
                    Optional<Category> category = categoryRepository.findById(request.getCategoryId());
                    if (category.isPresent() && !data.getCategory().equals(category.get())) {
                        data.setCategory(category.get());
                    }
                }
                if (request.getProcedureId() != 0) {
                    Optional<Procedure> procedure = procedureRepository.findById(request.getProcedureId());
                    if (procedure.isPresent() && !data.getProcedure().equals(procedure.get())) {
                        data.setProcedure(procedure.get());
                    }
                }
                if (request.getLanguageId() != 0) {
                    Optional<Language> language = languageRepository.findById(request.getLanguageId());
                    if (language.isPresent() && !data.getLanguage().equals(language.get())) {
                        data.setLanguage(language.get());
                    }
                }
                if (request.getAmount() != 0 && request.getAmount() != data.getAmount()) {
                    data.setAmount(request.getAmount());
                    if (product.get().getAmount()==0)   data.setActive(false);
                }
                if (request.getPrice() != 0 && request.getPrice() != data.getPrice()) {
                    data.setPrice(request.getPrice());
                }
                if (request.getDescription() != null && !request.getDescription().equals(data.getDescription())) {
                    data.setDescription(request.getDescription());
                }
                if (request.getName() != null && !request.getName().equals(data.getName())) {
                    data.setName(request.getName());
                }
                if (request.getPublishingYear() != 0 && request.getPublishingYear() != data.getPublishingYear()) {
                    data.setPublishingYear(request.getPublishingYear());
                }
                return toProductResponse(productRepository.save(data));
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public ProductResponse changeStatus(int id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                product.get().setActive(!product.get().isActive());
                if (product.get().isActive() && product.get().getAmount()==0)   throw new BadRequestException("Product is sold out");
                return toProductResponse(productRepository.save(product.get()));
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean delete(int id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                productRepository.delete(product.get());
                return true;
            }
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private List<ProductResponse> toProductResponses(List<Product> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(toProductResponse(product));
        }
        return productResponses;
    }

    private ProductResponse toProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setDesc(product.getDescription());
        response.setName(product.getName());
        response.setActive(product.isActive());
        response.setAmount(product.getAmount());
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(product.getPrice())+ " đ";//revert to VND
        response.setPrice(moneyString);
        response.setLanguage(product.getLanguage());
        response.setPublishingYear(product.getPublishingYear());
        response.setCategory(product.getCategory());
        response.setProcedure(product.getProcedure());
        return response;
    }

}
