package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@Api("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepo usuarioRepository;
    // get Usuarios
    @GetMapping(path ="/all")
    @ApiOperation(value="find all users", response =Usuario.class)
    public List<Usuario> getAllUsuario(){

        return usuarioRepository.findAll();
    }
    /*@GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }*/

    // get Usuario  by id
    @GetMapping("/usuarios/{id}")
    @ApiOperation(value = "Find Student By Id", response = Usuario.class)
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") Long usuarioId) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("el usuario con este id " + usuarioId + " no se encontró"));
        return ResponseEntity.ok().body(usuario);
    }
    /*@PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }*/
    // save Usuario
    @PostMapping(path= "/save")
    @ApiOperation(value = "Insert Student", response = Usuario.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);

    }
    /*@PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }*/
    // update Usuario
    public  ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "id") Long usuarioId,
                                                  @RequestBody Usuario usuarioDetails) throws  ResourceNotFoundException{
        Usuario usuario = usuarioRepository.findById(usuarioId)
                                           .orElseThrow(() -> new ResourceNotFoundException("el usuario con este id " + usuarioId + " no se encontró"));
        usuario.setUsuario(usuarioDetails.getUsuario());
        usuario.setPassword(usuarioDetails.getPassword());
        final Usuario updateUsuario =  usuarioRepository.save(usuario);
        return  ResponseEntity.ok(updateUsuario);


    }
    /*@DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }*/
    // delete Usuario
    @DeleteMapping(path = "/delete")
    @ApiOperation(value = "Delete Student By Id", response = Usuario.class)
    public Map<String, Boolean> deleteUsuario(@PathVariable(value = "id") Long usuarioId) throws  ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                                           .orElseThrow(()-> new ResourceNotFoundException("el usuario con este id " + usuarioId + " no se encontró"));
        usuarioRepository.delete(usuario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
}
