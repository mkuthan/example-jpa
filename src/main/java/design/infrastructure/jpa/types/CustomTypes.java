package design.infrastructure.jpa.types;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import design.domain.example6.clob.ClobHolder;

@MappedSuperclass
@TypeDefs({ @TypeDef(defaultForType = ClobHolder.class, typeClass = ClobHolderType.class) })
public class CustomTypes {
}