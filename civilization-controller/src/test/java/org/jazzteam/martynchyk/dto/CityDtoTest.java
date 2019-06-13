package org.jazzteam.martynchyk.dto;

import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CityDtoTest {

    private Civilization civilization;
    private City moscow;

    @BeforeMethod
    public void setUp() {
        civilization = new Civilization();
        moscow = new City(civilization);
        moscow.setName("Moscow");
        civilization.addCity(moscow);
        civilization.setCapital(moscow);
        civilization.setName("Russia");
    }

    @Test
    public void testCityEntityToCityDto() {
        CityDto cityDto = new CityDto(moscow);

        assertEquals(cityDto.getName(), moscow.getName());
        assertEquals(cityDto.getCivilization(), moscow.getCivilization());
        assertEquals(cityDto.getResources(), moscow.getResources());
        assertEquals(cityDto.getUnits(), moscow.getUnits());
        assertEquals(cityDto.getImprovingBuildings(), moscow.getImprovingBuildings());
        assertEquals(cityDto.getProducingBuildings(), moscow.getProducingBuildings());

    }

    @Test
    public void testCityDtoToCityEntity() {
        CityDto cityDto = new CityDto();
        cityDto.setName(moscow.getName());
        cityDto.setCivilization(moscow.getCivilization());
        cityDto.setResources(moscow.getResources());
        cityDto.setUnits(moscow.getUnits());
        cityDto.setImprovingBuildings(moscow.getImprovingBuildings());
        cityDto.setProducingBuildings(moscow.getProducingBuildings());

        City actual = cityDto.convertToEntity();

        assertTrue(cityDto.getName().equals(actual.getName())
                && cityDto.getCivilization().equals(actual.getCivilization())
                && cityDto.getResources().equals(actual.getResources())
                && cityDto.getUnits().equals(actual.getUnits())
                && cityDto.getImprovingBuildings().equals(actual.getImprovingBuildings())
                && cityDto.getProducingBuildings().equals(actual.getProducingBuildings())
        );
    }

    @Test
    public void testCityConvertingEntityToDtoToEntity() {
        CityDto cityDto = new CityDto(moscow);
        City actual = cityDto.convertToEntity();
        assertEquals(moscow, actual);
    }


}