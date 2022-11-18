package timemachi.springdockerredistodoList.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;
import timemachi.springdockerredistodoList.model.ToDo;
import timemachi.springdockerredistodoList.model.ToDoList;

import java.util.Arrays;
import java.util.Optional;

@RestController("/")
public class TodoListController {
    private ToDoList sample = new ToDoList(1, Arrays.asList(new ToDo(1, "Comment", false), new ToDo(2, "Clap", false),
            new ToDo(3, "Follow Author", false)), false);

    @GetMapping
    @Cacheable(value = "todo-list" , key="'getList'")
    public ToDoList getList() {
        return sample;
    }

    @GetMapping("/todo/{id}")
    @Cacheable(value = "todo-single", key = "#id")
    public Optional<ToDo> getToDo(@PathVariable("id") long id) {
        return sample.getTasks().stream().filter(x -> x.getId() == id).findFirst();
    }

    @PutMapping("/todo")
    @CachePut(value = "todo-single", key = "#toDo.id")
    @CacheEvict(value="todo-list" , key="'getList'")
    public ToDoList addToDo(@RequestBody ToDo toDo) {
        sample.getTasks().add(toDo);
        return sample;
    }

    @PostMapping("/todo/{id}")
    @CachePut(value = "todo-single", key = "#id")
    @CacheEvict(value="todo-list", key="'getList'")
    public ToDo updateToDo(@PathVariable("id") long id, @RequestBody ToDo toDo) throws Exception {
        Optional<ToDo> item = sample.getTasks().stream().filter(x -> x.getId() == id).findFirst();
        if (item.isPresent()) {
            item.get().setCompleted(toDo.isCompleted());
            return item.get();
        } else {
            throw new Exception("To Do item not found");
        }
    }

    @DeleteMapping("/todo/{id}")
    @Caching(evict = {
            @CacheEvict(value = "todo-single", key = "#id"),
            @CacheEvict(value="todo-list", key="'getList'")
    })
    public void deleteToDo(@PathVariable("id") long id) throws Exception {
        Optional<ToDo> item = sample.getTasks().stream().filter(x -> x.getId() == id).findFirst();
        if (item.isPresent()) {
            sample.getTasks().remove(item.get());
        } else {
            throw new Exception("To Do item not found");
        }
    }
}
