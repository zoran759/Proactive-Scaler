package com.dimamon.Service;

import com.dimamon.Dao.MeasurementsService;
import com.dimamon.Dao.StudentDao;
import com.dimamon.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by dimamon on 11/15/16.
 */
@Service
public class StudentService {

    @Autowired
    @Qualifier("fakeData")
    private StudentDao studentDao;
    
    @Autowired
    private MeasurementsService measurementsService;

    @Autowired
    private ResourcesService resourcesService;

    public Collection<Student> getAll() {
        measurementsService.measure(0, "getAll",
                resourcesService.getCpuLoad(), resourcesService.getFreeMemory());
        return this.studentDao.retrieveAll();
    }

    public Student getById(int id) {
        measurementsService.measure(id, "get",
                resourcesService.getCpuLoad(), resourcesService.getFreeMemory());
        return this.studentDao.retrieveById(id);
    }

    public Student removeById(int id) {
        measurementsService.measure(id, "remove",
                resourcesService.getCpuLoad(), resourcesService.getFreeMemory());
        return this.studentDao.remove(id);
    }

    public void update(final Student student) {
        measurementsService.measure(student.getId(), "update",
                resourcesService.getCpuLoad(), resourcesService.getFreeMemory());
        this.studentDao.update(student);
    }

    public void add(final Student student) {
        measurementsService.measure(student.getId(), "insert",
                resourcesService.getCpuLoad(), resourcesService.getFreeMemory());
        this.studentDao.add(student);
    }
}
