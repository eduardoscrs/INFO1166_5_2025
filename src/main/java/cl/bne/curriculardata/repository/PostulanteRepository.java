package cl.bne.curriculardata.repository;

import cl.bne.curriculardata.domain.Postulante;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostulanteRepository {

  private final Map<Long, Postulante> store = new ConcurrentHashMap<>();
  private final AtomicLong seq = new AtomicLong(0);

  public Postulante save(Postulante p) {
    if (p.getId() == null) p.setId(seq.incrementAndGet());
    store.put(p.getId(), p);
    return p;
  }

  public Optional<Postulante> findById(Long id) { return Optional.ofNullable(store.get(id)); }

  public List<Postulante> findAll() { return new ArrayList<>(store.values()); }

  public void deleteById(Long id) { store.remove(id); }

  public boolean existsById(Long id) { return store.containsKey(id); }
}
