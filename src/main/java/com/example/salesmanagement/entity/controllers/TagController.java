// package com.example.salesmanagement.entity.controllers;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.salesmanagement.entity.models.Tag;
// import com.example.salesmanagement.entity.services.TagService;

// @RestController
// @RequestMapping("/api/tags")
// public class TagController {
//     @Autowired
//     private TagService tagService;

//     @GetMapping("")
//     public ResponseEntity<List<Tag>> getAllTags() {
//         List<Tag> tags = tagService.getAllTags();
//         return ResponseEntity.ok(tags);
//     }


    
//     @GetMapping("/{id}/show")
//     public ResponseEntity<Tag> getTagById(@PathVariable(value = "id") String id) {
//         Tag tag = tagService.getTagById(id);
//         return ResponseEntity.ok(tag);
//     }


//     @PostMapping("/store")
//     public  ResponseEntity<?> store(@RequestBody Tag tag){
//         tagService.createTag(tag);
//         return ResponseEntity.ok(tag);
//     }



//     @PutMapping("/{id}/update")
//     public void updateTag(@PathVariable("id") String id, @RequestBody Tag tag) {
//         tagService.updateTag(id, tag);
//         System.out.println(tag);
//     }



//     @DeleteMapping("/{id}/delete")
//     public ResponseEntity<Void> deleteTag(@PathVariable(value = "id") String id) {
//         tagService.deleteTag(id);
//         return ResponseEntity.noContent().build();
//     }

// }
