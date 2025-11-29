package use_case.make_listing;

import entity.Product;
import java.util.UUID;
import java.util.Base64;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MakeListingInteractor implements MakeListingInputBoundary {
    
    private final MakeListingOutputBoundary outputBoundary;
    private final MakeListingDataAccessInterface dataAccessInterface;

    public MakeListingInteractor(MakeListingOutputBoundary outputBoundary,
                                 MakeListingDataAccessInterface dataAccessInterface) {
        this.outputBoundary = outputBoundary;
        this.dataAccessInterface = dataAccessInterface;
    }

    /**
     * Execute implementation
     */
    public void execute(MakeListingInputData inputData) {
        Product product = new Product(
            inputData.getProductName(),
            inputData.getPrice(),
            UUID.randomUUID().toString(),
            Base64.getEncoder().encodeToString(
                Files.readAllBytes(Paths.get(inputData.getFilePath()))
            ),
            
        );
    }
}