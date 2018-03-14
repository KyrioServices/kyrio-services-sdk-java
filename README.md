# Java SDK to Kyrio Online Services

This SDK provides full access to Kyrio Online Services public API.

## Install

Add maven dependencies to jackson and kyrio-service into your pom.xml file
```bash
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.7.5</version>
</dependency>
<dependency>
    <groupId>com.kyrio</groupId>
    <artifactId>kyrio-services</artifactId>
    <version>1.0</version>
</dependency>
```

## Use

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyrio.services.KyrioAccount;
import com.kyrio.services.serviceability.ServiceabilityClient;
import com.kyrio.services.serviceability.ServiceabilityResult;

public class ServiceabilitySample {

	public static void main(String[] args) {
        KyrioAccount account = new KyrioAccount();
        account.setClientId("999999");

        ServiceabilityClient client = account.createServiceabilityClient();
        try
        {
            ServiceabilityResult[] result = client.determineBusinessServiceability(
                "858 Coal Creek Circle", null, "Louisville", "CO", "80027", "US"
            );

            String json = new ObjectMapper().writeValueAsString(result);
            System.out.println(json);
        } catch (Exception ex) {
            System.err.println("Failed to call serviceability API");
            System.err.println(ex);
        }		
	}
	
}
```

## License

This SDK is distributed under MIT license and free to use for all Kyrio clients.