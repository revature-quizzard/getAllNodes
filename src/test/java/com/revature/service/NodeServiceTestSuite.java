package com.revature.service;

import com.revature.models.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NodeServiceTestSuite {
    NodeService sut;

    @BeforeEach
    public void caseSetUp() {
        sut = new NodeService();
    }

    @AfterEach
    public void caseCleanUp() {
        sut = null;
    }

    @Test
    public void isValidParent_returnsTrue_whenGivenValidParent() {
        //Arrange
        Node mockParent = new Node("valid", "valid", Arrays.asList(),"NULL", "valid", 0, null, "valid", Arrays.asList());

        //Act
        boolean result = sut.isValidParent(mockParent);

        //Assert
        assertTrue(result);
    }
}
