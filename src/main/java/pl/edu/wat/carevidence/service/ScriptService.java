package pl.edu.wat.carevidence.service;


import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.carevidence.repository.SamochodRepository;
import pl.edu.wat.carevidence.repository.WypozyczajacyRepository;

@Service
@Slf4j
public class ScriptService {
    private final SamochodRepository samochodRepository;
    private final WypozyczajacyRepository wypozyczajacyRepository;

    @Autowired
    public ScriptService(SamochodRepository samochodRepository, WypozyczajacyRepository wypozyczajacyRepository) {
        this.samochodRepository = samochodRepository;
        this.wypozyczajacyRepository = wypozyczajacyRepository;
    }

    public String exec(String script) {
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()) {
            var bindings = context.getBindings("js");
            bindings.putMember("samochodRepository", samochodRepository);
            bindings.putMember("wypozyczajacyRepository", wypozyczajacyRepository);
            return context.eval("js", script).toString();
        } catch (PolyglotException e) {
            log.error("Error executing", e);
            return e.getMessage() + "\n" + e.getSourceLocation().toString();
        }
    }
}