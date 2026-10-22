

package com.craft;

import jakarta.persistence.*;
import java.util.List;

public class App {

    // Create EntityManagerFactory once
    public static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("studentPU");

    // Get EntityManager whenever needed
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // CREATE

    public static void createStudent(Student student) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();

        em.close();
    }

    // READ

    // READ 1: Get student by ID
    public static Student getStudentById(int id) {
        EntityManager em = getEntityManager();

        Student student = em.find(Student.class, id);

        em.close();
        return student;
    }

    // READ 2: Get all students
    public static List<Student> getAllStudents() {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s",
                Student.class
        ).getResultList();

        em.close();
        return students;
    }

    // READ 3: Find students by exact name
    public static List<Student> getStudentsByName(String name) {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s WHERE s.name = :name",
                Student.class
        ).setParameter("name", name).getResultList();

        em.close();
        return students;
    }

    // READ 4: Find students by name, ignoring case
    public static List<Student> getStudentsByNameCaseInsensitive(
            String name) {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s " +
                        "WHERE LOWER(s.name) = LOWER(:name)",
                Student.class
        ).setParameter("name", name).getResultList();

        em.close();
        return students;
    }

    // READ 5: Find students by technology
    public static List<Student> getStudentsByTech(String tech) {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s WHERE s.tech = :tech",
                Student.class
        ).setParameter("tech", tech).getResultList();

        em.close();
        return students;
    }

    // READ 6: Names starting with a letter
    public static List<Student> getStudentsByNameStartingWith(
            String letter) {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s WHERE s.name LIKE :pattern",
                Student.class
        ).setParameter("pattern", letter + "%").getResultList();

        em.close();
        return students;
    }

    // READ 7: Names containing a substring
    public static List<Student> getStudentsByNameContaining(
            String substring) {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                        "SELECT s FROM Student s WHERE s.name LIKE :pattern",
                        Student.class
                ).setParameter("pattern", "%" + substring + "%")
                .getResultList();

        em.close();
        return students;
    }

    // READ 8: Students with ID greater than a value
    public static List<Student> getStudentsWithIdGreaterThan(
            int minId) {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s WHERE s.id > :minId",
                Student.class
        ).setParameter("minId", minId).getResultList();

        em.close();
        return students;
    }

    // READ 9: Students whose IDs are within a range
    public static List<Student> getStudentsWithIdBetween(
            int startId, int endId) {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                        "SELECT s FROM Student s " +
                                "WHERE s.id BETWEEN :startId AND :endId",
                        Student.class
                ).setParameter("startId", startId)
                .setParameter("endId", endId)
                .getResultList();

        em.close();
        return students;
    }

    // READ 10: Students with selected IDs
    public static List<Student> getStudentsByIdList(
            List<Integer> idList) {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s WHERE s.id IN :idList",
                Student.class
        ).setParameter("idList", idList).getResultList();

        em.close();
        return students;
    }

    // READ 11: Order students by name ascending
    public static List<Student> getAllStudentsOrderByNameAsc() {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s ORDER BY s.name ASC",
                Student.class
        ).getResultList();

        em.close();
        return students;
    }

    // READ 12: Order students by name descending
    public static List<Student> getAllStudentsOrderByNameDesc() {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s ORDER BY s.name DESC",
                Student.class
        ).getResultList();

        em.close();
        return students;
    }

    // READ 13: Get the first N students
    public static List<Student> getFirstNStudents(int n) {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s ORDER BY s.id ASC",
                Student.class
        ).setMaxResults(n).getResultList();

        em.close();
        return students;
    }

    // READ 14: Pagination
    public static List<Student> getStudentsWithPagination(
            int offset, int limit) {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                        "SELECT s FROM Student s ORDER BY s.id ASC",
                        Student.class
                ).setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

        em.close();
        return students;
    }

    // READ 15: Count all students
    public static long countAllStudents() {
        EntityManager em = getEntityManager();

        long count = em.createQuery(
                "SELECT COUNT(s) FROM Student s",
                Long.class
        ).getSingleResult();

        em.close();
        return count;
    }

    // READ 16: Count students for each technology
    public static List<Object[]> countStudentsByTech() {
        EntityManager em = getEntityManager();

        List<Object[]> results = em.createQuery(
                "SELECT s.tech, COUNT(s) FROM Student s " +
                        "GROUP BY s.tech",
                Object[].class
        ).getResultList();

        em.close();
        return results;
    }

    // READ 17: Find distinct technologies
    public static List<String> getDistinctTechs() {
        EntityManager em = getEntityManager();

        List<String> techs = em.createQuery(
                "SELECT DISTINCT s.tech FROM Student s",
                String.class
        ).getResultList();

        em.close();
        return techs;
    }

    // READ 18: Average student ID
    public static Double getAverageId() {
        EntityManager em = getEntityManager();

        Double average = em.createQuery(
                "SELECT AVG(s.id) FROM Student s",
                Double.class
        ).getSingleResult();

        em.close();
        return average;
    }

    // READ 19: Maximum student ID
    public static Integer getMaxId() {
        EntityManager em = getEntityManager();

        Integer maxId = em.createQuery(
                "SELECT MAX(s.id) FROM Student s",
                Integer.class
        ).getSingleResult();

        em.close();
        return maxId;
    }

    // READ 20: Students with the longest names
    public static List<Student> getStudentsWithLongestNames() {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s WHERE LENGTH(s.name) = " +
                        "(SELECT MAX(LENGTH(s2.name)) FROM Student s2)",
                Student.class
        ).getResultList();

        em.close();
        return students;
    }

    // READ 21: Filter by technology and name pattern
    public static List<Student> getStudentsByTechAndNamePattern(
            String tech, String pattern) {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                        "SELECT s FROM Student s WHERE s.tech = :tech " +
                                "AND s.name LIKE :pattern",
                        Student.class
                ).setParameter("tech", tech)
                .setParameter("pattern", "%" + pattern + "%")
                .getResultList();

        em.close();
        return students;
    }

    // READ 22: Students with NULL technology
    public static List<Student> getStudentsWithNullTech() {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s WHERE s.tech IS NULL",
                Student.class
        ).getResultList();

        em.close();
        return students;
    }

    // READ 23: Students with non-NULL technology
    public static List<Student> getStudentsWithNonNullTech() {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s WHERE s.tech IS NOT NULL",
                Student.class
        ).getResultList();

        em.close();
        return students;
    }

    // READ 24: Students with the most common technology
    public static List<Student> getStudentsWithMostCommonTech() {
        EntityManager em = getEntityManager();

        List<Object[]> counts = em.createQuery(
                "SELECT s.tech, COUNT(s) FROM Student s " +
                        "WHERE s.tech IS NOT NULL " +
                        "GROUP BY s.tech ORDER BY COUNT(s) DESC",
                Object[].class
        ).setMaxResults(1).getResultList();

        if (counts.isEmpty()) {
            em.close();
            return List.of();
        }

        String mostCommonTech = (String) counts.get(0)[0];

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s WHERE s.tech = :tech",
                Student.class
        ).setParameter("tech", mostCommonTech).getResultList();

        em.close();
        return students;
    }

    // READ 25: Students whose technology is unique
    public static List<Student> getStudentsWithUniqueTechs() {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s WHERE s.tech IN (" +
                        "SELECT s2.tech FROM Student s2 " +
                        "WHERE s2.tech IS NOT NULL " +
                        "GROUP BY s2.tech HAVING COUNT(s2) = 1)",
                Student.class
        ).getResultList();

        em.close();
        return students;
    }

    // READ 26: Names longer than the average name length
    public static List<Student> getStudentsWithLongerThanAverageNames() {
        EntityManager em = getEntityManager();

        List<Student> students = em.createQuery(
                "SELECT s FROM Student s WHERE LENGTH(s.name) > " +
                        "(SELECT AVG(LENGTH(s2.name)) FROM Student s2)",
                Student.class
        ).getResultList();

        em.close();
        return students;
    }

    // ==================== UPDATE ====================

    // UPDATE
    public static void updateStudentName(String newName, int id) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Student student = em.find(Student.class, id);

        if (student != null) {
            student.setName(newName);
        } else {
            System.out.println("Student not found: " + id);
        }

        em.getTransaction().commit();
        em.close();
    }

    // UPDATE 2: Update name and technology
    public static void updateStudentNameAndTech(
            String newName, String newTech, int id) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Student student = em.find(Student.class, id);

        if (student != null) {
            student.setName(newName);
            student.setTech(newTech);
        } else {
            System.out.println("Student not found: " + id);
        }

        em.getTransaction().commit();
        em.close();
    }

    // UPDATE 3: Update technology using JPQL
    public static int updateStudentTech(int id, String newTech) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        int updated = em.createQuery(
                        "UPDATE Student s SET s.tech = :newTech " +
                                "WHERE s.id = :id"
                ).setParameter("newTech", newTech)
                .setParameter("id", id)
                .executeUpdate();

        em.getTransaction().commit();
        em.close();

        return updated;
    }

    // UPDATE 4: Update technology by matching a pattern
    public static int updateTechByPattern(
            String oldPattern, String newTech) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        int updated = em.createQuery(
                        "UPDATE Student s SET s.tech = :newTech " +
                                "WHERE s.tech LIKE :oldPattern"
                ).setParameter("newTech", newTech)
                .setParameter("oldPattern", "%" + oldPattern + "%")
                .executeUpdate();

        em.getTransaction().commit();
        em.close();

        return updated;
    }

    // ==================== DELETE ====================

    // DELETE 1: Delete a student by ID
    public static int deleteStudentById(int id) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        int deleted = em.createQuery(
                "DELETE FROM Student s WHERE s.id = :id"
        ).setParameter("id", id).executeUpdate();

        em.getTransaction().commit();
        em.close();

        return deleted;
    }

    // DELETE 2: Delete students by technology
    public static int deleteStudentsByTech(String tech) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        int deleted = em.createQuery(
                "DELETE FROM Student s WHERE s.tech = :tech"
        ).setParameter("tech", tech).executeUpdate();

        em.getTransaction().commit();
        em.close();

        return deleted;
    }

    // ==================== MAIN ====================

    public static void main(String[] args) {

        // TEST DATA: Insert students into the database

        Student s1 = new Student();
        s1.setName("muferiat");
        s1.setTech("Java");
        createStudent(s1);

        Student s2 = new Student();
        s2.setName("Ebise");
        s2.setTech("Python");
        createStudent(s2);

        Student s3 = new Student();
        s3.setName("Selam");
        s3.setTech("Java");
        createStudent(s3);

        Student s4 = new Student();
        s4.setName("Tekle");
        s4.setTech("CppS");
        createStudent(s4);


        // READ: Display all students
        System.out.println("All students:");
        getAllStudents().forEach(System.out::println);

        // READ: Get student by ID
        System.out.println("\nStudent with ID 1:");
        System.out.println(getStudentById(1));

        // READ: Find students by name
        System.out.println("\nStudents named John Snow:");
        getStudentsByName("John Snow")
                .forEach(System.out::println);

        // READ: Find students by technology
        System.out.println("\nStudents using Java:");
        getStudentsByTech("Java")
                .forEach(System.out::println);

        // READ: Search names containing text
        System.out.println("\nNames containing 'Ali':");
        getStudentsByNameContaining("Ali")
                .forEach(System.out::println);

        // READ: Count all students
        System.out.println("\nTotal students: " + countAllStudents());

        // UPDATE
        updateStudentName("Alemayehu", 1);
        updateStudentNameAndTech("Alemayehu", "Python", 1);

        int updated = updateStudentTech(1, "Python");
        System.out.println("Updated students: " + updated);

        // DELETE examples: uncomment ONE when needed
        int deleted = deleteStudentById(1);
        System.out.println("Deleted students: " + deleted);



        emf.close();
    }
}


