package com.hazymoonstudio.legaldecision.network.model

import com.hazymoonstudio.legaldecision.domain.model.Component
import com.hazymoonstudio.legaldecision.domain.util.DomainMapper
import com.hazymoonstudio.legaldecision.utils.ComponentType

class ComponentDtoMapper : DomainMapper<ComponentDto, Component> {
    override fun mapToDomainModel(model: ComponentDto): Component {
        return Component(
            type = ComponentType.create(model.type),
            text = model.text,
            date = model.date,
            time = model.time
        )
    }

    override fun mapFromDomainModel(domainModel: Component): ComponentDto {
        return ComponentDto(
            type = domainModel.type.type,
            text = domainModel.text,
            date = domainModel.date,
            time = domainModel.time
        )
    }

    fun toDomainList(initial: List<ComponentDto>): List<Component>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Component>): List<ComponentDto>{
        return initial.map { mapFromDomainModel(it) }
    }
}