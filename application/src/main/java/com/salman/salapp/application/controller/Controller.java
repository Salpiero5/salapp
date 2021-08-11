package com.salman.salapp.application.controller;

import com.salman.salapp.application.service.CustomerService;
import com.salman.salapp.application.service.FileStorageService;
import com.salman.salapp.library.entity.Customer;
import com.salman.salapp.library.exceptions.NullIdException;
import com.salman.salapp.library.filter.CustomerFilter;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/customers")
public class Controller {

    private final CustomerService customerService;

    @Autowired
    private FileStorageService fileStorageService;

    public Controller(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Value("${welcome.msg}")
    private String welcomeMsg;

    @Value("${some.string}")
    private String randomString;

    @Value("${download.path}")
    private String dlPath;


    /**
     * /**
     * Method for testing application properties field
     *
     * @return welcomeMsg + randomString
     */
    @GetMapping(value = "/welcome")
    public String welcome() {
        return welcomeMsg + " + random string -> " + randomString;
    }

    /**
     * Search method with params with Specification Resolver Api
     *
     * @param spec this is object of Specification interface for suitable filter search with
     *             params
     * @return customers with specified filter on it
     */
    @CrossOrigin(value = "*", allowedHeaders = "*")
    //@Secured("ROLE_USER")
    @Transactional
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomers(
            @And({
                    @Spec(path = "id", params = "id", spec = EqualIgnoreCase.class),
                    @Spec(path = "firstName", params = "firstName", spec = LikeIgnoreCase.class),
                    @Spec(path = "lastName", params = "lastName", spec = LikeIgnoreCase.class),
                    @Spec(path = "email", params = "email", spec = LikeIgnoreCase.class),
                    @Spec(path = "phone", params = "phone", spec = LikeIgnoreCase.class),
            }) Specification<Customer> spec) {

        List<Customer> customers = customerService.getCustomers(spec);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    /**
     * Search method with filter with Specification JPA
     *
     * @param customerFilter from the client
     * @return customers with specified filter on it
     */
    @CrossOrigin(value = "*", allowedHeaders = "*")
    @PostMapping(value = "/filter")
    public ResponseEntity<List<Customer>> getCustomerWithBodyFilter(@RequestBody CustomerFilter customerFilter) {

        List<Customer> customers = customerService.getCustomersByFilter(customerFilter);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @CrossOrigin(value = "*", allowedHeaders = "*")
    //@Secured("ROLE_ADMIN")
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable(value = "id") long id) {

        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isEmpty()) {
            throw new NullIdException("The customer isn't existed with this id {" + id + "}");
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/insert-customer")
    public ResponseEntity<HttpStatus> insertCustomer(@RequestBody Customer request) {

        customerService.insertCustomer(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/first-name/{name}")
    public ResponseEntity<Optional<List<Customer>>> getCustomerByFirstNameIgnoreCase(@PathVariable(value = "name") String name) {

        Optional<List<Customer>> customers = customerService.getCustomerByFirstNameIgnoreCase(name);
        if (customers.isEmpty()) {
            throw new NullIdException("The customer isn't existed with this name {" + name + "}");
        } else {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
    }

    //@Secured("ROLE_USER")
    @GetMapping(value = "/sorted-by-name")
    public ResponseEntity<Optional<List<Customer>>> getCustomersSorted() {

        Optional<List<Customer>> customers = customerService.getCustomersSorted();
        if (customers.isEmpty()) {
            throw new NullIdException("The customer isn't existed");
        } else {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/phone-by-pattern/{phone}")
    public ResponseEntity<Optional<List<Customer>>> getCustomersByPhoneWithPattern(@PathVariable(value = "phone") String phone) {

        Optional<List<Customer>> customers = customerService.getCustomersByPhoneWithPattern(phone);
        if (customers.isEmpty()) {
            throw new NullIdException("The customer isn't existed with this phone {" + phone + "}");
        } else {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
    }


    /**
     * This is for downloading a resource from resource folder with rest api
     *
     * @param fileName name of the downloaded file
     * @param response raw type it will be downloaded to the client hard
     * @author Salman Peirovi
     * @since 2021
     */
    @RequestMapping(value = "/download/{file_name}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("file_name") String fileName,
                             HttpServletResponse response) {

        response.setContentType("application/octet-stream");
        InputStream inputStream;
        Resource resource = new ClassPathResource(dlPath);
        try {
            // get your file as InputStream
            inputStream = resource.getInputStream();
            // copy it to response's OutputStream
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }

        //Or just write below with ResponseEntity<Resource> method return type!
        //return ResponseEntity.ok().body(resource);
    }

    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/data/")
                .path(fileName)
                .toUriString();
    }

}
