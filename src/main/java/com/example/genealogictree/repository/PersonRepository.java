package com.example.genealogictree.repository;

import com.example.genealogictree.model.entitygenealogictree.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query(value = "select * from tb_biological_children bc join tb_person p on bc.id_person_father_and_mother = p.id where id_person_biological_children = :id", nativeQuery = true)
    public List<Person> findBiologicalParents(@Param("id") Integer idPersonForDelete);

    @Query(value = "select count(*) from tb_person where layer < :layer " +
            "and (id = (select person_biological_father from tb_person where id = :id) " +
            "or id = (select person_biological_mother from tb_person where id = :id))", nativeQuery = true)
    public Integer personHasOneBiologicalParentsToTheMainBranch(@Param("id") Integer id, @Param("layer") Integer layer);

    @Query(value = "select * from tb_person where id in(select id_person_biological_children from tb_biological_children where id_person_father_and_mother = :idParent) " +
            "and person_biological_father = (select person_biological_father from tb_person where id = :id) " +
            "and person_biological_mother = (select person_biological_mother from tb_person where id = :id) and id != :id", nativeQuery = true)
    public List<Person> checkBrothersPerson(@Param("id")Integer idPersonDelete,@Param("idParent") Integer idParentDelete);

    @Transactional
    @Modifying
    @Query(value = "delete from tb_biological_children where id_person_father_and_mother = :idParent and id_person_biological_children = :idChildren", nativeQuery = true)
    public void deleteBiologicalChildren(@Param("idParent") Integer idParent, @Param("idChildren") Integer idChildren);

    @Transactional
    @Modifying
    @Query(value = "delete from tb_adoptive_children where id_person_father_and_mother = :idParent and id_person_adoptive_children = :idChildren", nativeQuery = true)
    public void deleteAdoptiveChildren(@Param("idParent") Integer idParent, @Param("idChildren") Integer idChildren);

    @Transactional
    @Modifying
    @Query(value = "update tb_person " +
            "set person_biological_father =  case " +
            "when person_biological_father = :idParent " +
            "then null " +
            "else person_biological_father " +
            "end, " +
            "person_biological_mother = case " +
            "when person_biological_mother = :idParent " +
            "then null " +
            "else person_biological_mother " +
            "end " +
            "where id = :idChildren", nativeQuery = true)
    public void deleteFatherOrMotherBiologic(@Param("idParent") Integer idParent, @Param("idChildren") Integer idChildren);

    @Transactional
    @Modifying
    @Query(value = "delete from tb_adoptive_parents where id_person_adoptive_fk = :idChildren and id_person_father_adoptive_fk = :idParent", nativeQuery = true)
    public void deleteFatherOrMotherAdoptive(@Param("idParent") Integer idParent, @Param("idChildren") Integer idChildren);

    @Transactional
    @Modifying
    @Query(value = "update tb_genealogic_tree set initial_person_fk = null where initial_person_fk = :id", nativeQuery = true)
    public void deleteInitialPersonOfTheGenealogicTree(@Param("id") Integer idPerson);


}
