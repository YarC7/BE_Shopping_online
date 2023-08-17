// package com.example.salesmanagement.entity.services;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;

// import com.example.salesmanagement.entity.models.Tag;
// import com.example.salesmanagement.entity.repositories.TagRepository;
// import com.example.salesmanagement.entity.utilities.Time;

// @Service
// public class TagService {
//     @Autowired
//     private TagRepository tagRepository;

//     public List<Tag> getAllTags(){
//         List<Tag> tags = new ArrayList<>();
//         tagRepository.findAll().forEach(tags::add);
//         return tags;
//     }
//     public Tag getTagById(String id) {
//         Optional<Tag> one_Tag = tagRepository.findById(id);
//         return one_Tag.orElse(null);
//     }

//     public void createTag(Tag tag) {
//         tagRepository.save(tag);
//     }

//     public ResponseEntity<Tag> updateTag(String id, Tag tag) {
//         Optional<Tag> optionalTag = tagRepository.findById(id);
    
//         if (!optionalTag.isPresent()) {
//             return ResponseEntity.notFound().build();
//         }
    
//         Tag existingTag = optionalTag.get();
    
//         existingTag.setTitle(tag.getTitle());
//         existingTag.setMetaTitle(tag.getMetaTitle());
//         existingTag.setSlug(tag.getSlug());
//         existingTag.setContent(tag.getContent());


//         // existingTag.setTagShippingAddress(tag.getTagShippingAddress());


//         existingTag.setupdatedAt(Time.getCurrentDate());

//         Tag updatedTag = tagRepository.save(existingTag);
    
//         return ResponseEntity.ok(updatedTag);
//     }

//     public void deleteTag(String id) {
//         tagRepository.deleteById(id);
//     }


// }
