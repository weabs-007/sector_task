package com.myform.test.service.impl;

import com.myform.test.model.Sector;
import com.myform.test.service.SectorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class SectorServiceImplTest {

    @Mock
    private SectorService service;

    @Test
    void should_find_all_sectors() {

        List<Sector> list = new ArrayList<>();
        list.add(new Sector()
                .setName("TestSector1")
                .setSectors(new ArrayList<>())
                .setParentSectorName(null));
        list.add(new Sector()
                .setName("TestSector2")
                .setSectors(new ArrayList<>())
                .setParentSectorName(null));
        list.add(new Sector()
                .setName("TestSector3")
                .setSectors(new ArrayList<>())
                .setParentSectorName(null));
        list.add(new Sector()
                .setName("TestSector4")
                .setSectors(new ArrayList<>())
                .setParentSectorName(null));

        when(service.getAll()).thenReturn(list);
        List<Sector> actualList = service.getAll();
        assertEquals(actualList.size(), list.size());
    }

}