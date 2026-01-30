package br.com.movieflix.Repository;

import br.com.movieflix.Entity.Streaming;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StreamingRepository extends JpaRepository<Streaming, Long> {
}
