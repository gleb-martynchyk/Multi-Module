package org.jazzteam.martynchyk.dto;

import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;

public class CivilizationDtoTest {

    private Civilization russia;
    private City moscow;

    @BeforeMethod
    public void setUp() {
        russia = new Civilization();
        moscow = new City(russia);
        moscow.setName("Moscow");
        russia.addCity(moscow);
        russia.setCapital(moscow);
        russia.setName("Russia");
    }

    @Test
    public void testCivilizationEntityToCivilizationDto() {
        CivilizationDto russiaDto = new CivilizationDto(russia);
        assertEquals(russiaDto.getId(), russia.getId());
        assertEquals(russiaDto.getName(), russia.getName());
        assertEquals(russiaDto.getCapital().convertToEntity(), russia.getCapital());
        assertEquals((russiaDto.getGold()), russia.getGold());
        assertEquals((russiaDto.getFaith()), russia.getFaith());

    }

    @Test
    public void testCivilizationDtoToCivilizationEntity() {
        CivilizationDto russiaDto = new CivilizationDto();

        russiaDto.setName(russia.getName());
        russiaDto.setCapital(new CityDto(russia.getCapital()));
        russiaDto.setGold(russia.getGold());
        russiaDto.setFaith(russia.getFaith());

        Civilization russiaEntity = russiaDto.convertToEntity();

        assertEquals(russiaDto.getName(), russiaEntity.getName());
        assertEquals(russiaDto.getCapital().convertToEntity(), russiaEntity.getCapital());
        assertEquals((russiaDto.getGold()), russiaEntity.getGold());
        assertEquals((russiaDto.getFaith()), russiaEntity.getFaith());
    }

    @Test
    public void testCityConvertingEntityToDtoToEntity() {
        CivilizationDto civilizationDto = new CivilizationDto(russia);
        Civilization civilizationEntity = civilizationDto.convertToEntity();
        assertEquals(russia, civilizationEntity);
    }
}