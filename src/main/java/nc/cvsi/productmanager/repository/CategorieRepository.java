package nc.cvsi.productmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nc.cvsi.productmanager.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findByNom(String nom);
}
