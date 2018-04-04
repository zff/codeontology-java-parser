package org.codeontology.extraction;

import com.hp.hpl.jena.rdf.model.RDFNode;
import org.codeontology.Ontology;
import spoon.reflect.code.CtLocalVariable;

public class LocalVariableWrapper extends AbstractWrapper<CtLocalVariable<?>> implements MemberWrapper<CtLocalVariable<?>> {

    public LocalVariableWrapper(CtLocalVariable<?> variable) {
        super(variable);
    }

    @Override
    public void extract() {
        tagType();
        tagName();
        tagJavaType();
        tagDeclaringElement();
    }

    @Override
    public String buildRelativeURI() {
        return getParent().getRelativeURI() + SEPARATOR + getElement().getSimpleName();
    }

    @Override
    protected RDFNode getType() {
        return Ontology.VARIABLE_ENTITY;
    }

    @Override
    public Wrapper<?> getDeclaringElement() {
        return getParent();
    }

    public void tagDeclaringElement() {
        new DeclaringElementTagger(this).tagDeclaredBy();
    }

    public void tagJavaType() {
        new JavaTypeTagger(this).tagJavaType(this.getParent());
    }
}
