package WebApp.DataBase.Services;

import WebApp.DataBase.Entities.Audit;
import WebApp.DataBase.Repositories.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private final AuditRepository repository;

    public AuditService(@Autowired AuditRepository repository) {
        this.repository = repository;
    }

    public void log(Audit audit) {
        this.repository.save(audit);
    }
}
