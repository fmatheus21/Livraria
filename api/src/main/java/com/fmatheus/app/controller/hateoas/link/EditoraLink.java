package com.fmatheus.app.controller.hateoas.link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import com.fmatheus.app.controller.resource.EditoraResource;

public class EditoraLink {

    public Link linkView(int id) {
        Link link = linkTo(methodOn(EditoraResource.class).get(id))
                .withRel("View")
                .withTitle("View record")
                .withType("GET")
                .withSelfRel();
        return link;
    }

    public Link linkDelete(int id) {
        Link link = linkTo(methodOn(EditoraResource.class).delete(id))
                .withRel("Delete")
                .withTitle("Delete record")
                .withType("DELETE");
        return link;
    }

    public Link linkUpdate(int id) {
        Link link = linkTo(methodOn(EditoraResource.class).put(id, null, null))
                .withRel("Update")
                .withTitle("Update record")
                .withType("PUT");
        return link;
    }

}
