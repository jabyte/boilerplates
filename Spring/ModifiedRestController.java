<#assign licenseFirst = "/*">
<#assign licensePrefix = " * ">
<#assign licenseLast = " */">
<#include "${project.licensePath}">

<#if package?? && package != "">
package ${package};

import ${package}.models.${name};
import ${package}.services.${name}Service;
</#if>

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

<#if crudMethods>
import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
</#if>
<#switch errorHandling>
  <#case 1>
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
  <#break>
  <#case 2>
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
  <#break>
</#switch>

/**
 *
 * @author ${user}
 */
@RestController
@RequestMapping("/url")
public class ${name}Controller {
<#if crudMethods>

    @Autowired
    ${name}Service obj${name}Service;

    @GetMapping()
    public ResponseEntity<List<${name}>> list() {
        List<${name}> result = obj${name}Service
                .getAll()
                .orElse(new ArrayList());

        return (new ResponseEntity(result, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<${name}> get(@PathVariable Integer id) {
        return obj${name}Service.getOne(id).isPresent()
                ? new ResponseEntity(obj${name}Service.getOne(id).get(), HttpStatus.FOUND)
                : new ResponseEntity("Does not exist.", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<${name}> put(@PathVariable String id, @RequestBody ${name} input) {
        return obj${name}Service.update(input).isPresent()
                ? new ResponseEntity(input, HttpStatus.OK)
                : new ResponseEntity("Update failed.", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<${name}> post(@RequestBody ${name} input) {
        return obj${name}Service.update(input).isPresent()
                ? new ResponseEntity(input, HttpStatus.CREATED)
                : new ResponseEntity("Operation failed.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<${name}> delete(@PathVariable Integer id) {
        try {
            obj${name}Service.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            // Ideally I should use loggin here.

            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
</#if>
<#switch errorHandling>
  <#case 1>

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Error message")
    public void handleError() {
    }
  <#break>
  <#case 2>

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Error message")
    public Object handleError(HttpServletRequest req, Exception ex) {
      Object errorObject=new Object();
      return errorObject;
    }
  <#break>
</#switch>

}
