# Java SDK to Kyrio Services

This SDK provides full access to Kyrio Services public API.

## Install

Add maven dependencies to jackson and kyrio-service into your pom.xml file
```bash
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.7.5</version>
</dependency>
<dependency>
    <groupId>com.kyrio.kyrioservices</groupId>
    <artifactId>kyrio-services</artifactId>
    <version>1.0.0</version>
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

## References

- [API Documentation](https://rawgit.com/KyrioServices/kyrio-services-sdk-java/master/doc/api/index.html)
- [Development Guide](https://github.com/KyrioServices/kyrio-services-sdk-java/blob/master/doc/Development.md)

## License

This SDK is distributed under MIT license and free to use for all Kyrio clients.
