package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryMock;
  private TorpedoStore secondaryMock;

  @BeforeEach
  public void init(){
    this.primaryMock = mock(TorpedoStore.class);
    this.secondaryMock = mock(TorpedoStore.class);

    this.ship = new GT4500(primaryMock, secondaryMock);
  }

  @Test
  public void fireTorpedo_Single_Success_Primary(){
    // Arrange
    when(primaryMock.getTorpedoCount()).thenReturn(10);
    when(secondaryMock.getTorpedoCount()).thenReturn(10);
    when(primaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primaryMock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success_Primary_Again(){
    // Arrange
    when(primaryMock.getTorpedoCount()).thenReturn(10);
    when(secondaryMock.getTorpedoCount()).thenReturn(0);
    when(primaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primaryMock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Primary_Then_Secondary(){
    // Arrange
    when(primaryMock.getTorpedoCount()).thenReturn(10);
    when(secondaryMock.getTorpedoCount()).thenReturn(10);
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, result2);
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Fail(){
    // Arrange
    when(primaryMock.getTorpedoCount()).thenReturn(0);
    when(secondaryMock.getTorpedoCount()).thenReturn(0);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_Fail(){
    // Arrange
    when(primaryMock.getTorpedoCount()).thenReturn(0);
    when(secondaryMock.getTorpedoCount()).thenReturn(0);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryMock.getTorpedoCount()).thenReturn(10);
    when(secondaryMock.getTorpedoCount()).thenReturn(10);
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(1)).fire(1);
  }

}
