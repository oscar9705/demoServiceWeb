package com.example.demo.repository;
import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends  JpaRepository<Usuario,Long> ,CrudRepository<Usuario, Long> {

}
