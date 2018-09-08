<#assign licenseFirst = "/*">
<#assign licensePrefix = " * ">
<#assign licenseLast = " */">
<#include "${project.licensePath}">

<#if package?? && package != "">
package ${package};

</#if>
import ${package}.models.${name};
import ${package}.repositories.${name}Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ${user}
 *
 */
@Service
public class ${name}Service {

    @Autowired
    ${name}Repository obj${name}Repository;

    public Optional<List<${name}>> getAll() {
        return Optional.ofNullable(obj${name}Repository.findAll());
    }

    public Optional<${name}> getOne(Integer id) {
        return obj${name}Repository.findById(id);
    }

    public Optional<${name}> create(${name} obj${name}) {
        return Optional.ofNullable(obj${name}Repository.save(obj${name}));
    }

    public Optional<${name}> update(${name} obj${name}) {
        return Optional.ofNullable(obj${name}Repository.save(obj${name}));
    }

    public void delete(Integer id) {
        obj${name}Repository.deleteById(id);
    }
}
