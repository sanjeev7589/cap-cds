package customer.products_service.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;

@Component
@ServiceName("AdminService")
public class AdminService implements EventHandler {
    @Autowired
    PersistenceService persistenceService;
    private Map<Object, Map<String, Object>> products = new HashMap<>();

    @On(event = CqnService.EVENT_CREATE, entity = "AdminService.Products")
    public void onCreate(CdsCreateEventContext context) {
        /*
         * context.getCqn().entries().forEach(e -> products.put(e.get("ID"), e));
         * context.setResult(context.getCqn().entries());
         */
        // Persist to DB
        persistenceService.run(context.getCqn());

        // Return result
        context.setResult(context.getCqn().entries());
    }

    @On(event = CqnService.EVENT_READ, entity = "AdminService.Products")
    public void onRead(CdsReadEventContext context) {
        context.setResult(products.values());
    }

}
