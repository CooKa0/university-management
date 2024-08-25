package university.personnel;

import university.interfaces.EventOrganizable;
import university.interfaces.Identifiable;
import university.management.Events;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Worker extends UniversityMember implements EventOrganizable, Identifiable  {
    private String department;
    private String id;
    private List<Events> events;

    public Worker(String name, LocalDate hireDate, String department) {
        super(name, hireDate);
        this.department = department;
    }

    private List<Events> getOrCreateEvents() {
        if (events == null) {
            events = new ArrayList<>();
        }
        return events;
    }

    @Override
    public String getRole() {
        return "Worker";
    }

    @Override
    public String getDetails() {
        return "Worker: " + name + ", Department: " + department + ", Hired on: " + hireDate;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return super.toString() + ", Department: " + department;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + department.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;

        Worker worker = (Worker) obj;

        return department.equals(worker.department);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void organizeEvent() {
        List<Events> events = getOrCreateEvents();
        System.out.println("Organizing all events...");
        for (Events event : events) {
            event.setStatus("Organized");
            System.out.println(event);
        }
    }

    @Override
    public void cancelEvent() {
        List<Events> events = getOrCreateEvents();
        System.out.println("Canceling all events...");
        for (Events event : events) {
            event.setStatus("Canceled");
            System.out.println(event);
        }
    }

    @Override
    public void createEvent() {
        List<Events> events = getOrCreateEvents();
        Events newEvent = new Events("New Event", LocalDate.now().plusDays(7), "Scheduled");
        events.add(newEvent);
        System.out.println("Created new event: " + newEvent);
    }

    @Override
    public void modifyEvent() {
        List<Events> events = getOrCreateEvents();
        if (events.isEmpty()) {
            System.out.println("No events to modify.");
            return;
        }
        // Update the status of the first event
        Events eventToModify = events.get(0);
        eventToModify.setStatus("Modified");
        System.out.println("Modified event: " + eventToModify);
    }

    public List<Events> getEvents() {
        return getOrCreateEvents();
    }
}