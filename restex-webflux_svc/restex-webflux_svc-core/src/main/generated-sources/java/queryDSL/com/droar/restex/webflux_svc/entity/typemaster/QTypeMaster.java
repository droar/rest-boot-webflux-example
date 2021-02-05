package com.droar.restex.webflux_svc.entity.typemaster;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTypeMaster is a Querydsl query type for TypeMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTypeMaster extends EntityPathBase<TypeMaster> {

    private static final long serialVersionUID = -238354255L;

    public static final QTypeMaster typeMaster = new QTypeMaster("typeMaster");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> discriminator = createNumber("discriminator", Integer.class);

    public final NumberPath<Integer> typeMasterId = createNumber("typeMasterId", Integer.class);

    public final StringPath value = createString("value");

    public QTypeMaster(String variable) {
        super(TypeMaster.class, forVariable(variable));
    }

    public QTypeMaster(Path<? extends TypeMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTypeMaster(PathMetadata metadata) {
        super(TypeMaster.class, metadata);
    }

}

