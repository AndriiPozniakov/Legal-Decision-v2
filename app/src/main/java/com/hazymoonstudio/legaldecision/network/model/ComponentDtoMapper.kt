package com.hazymoonstudio.legaldecision.network.model

import com.hazymoonstudio.legaldecision.domain.model.Component
import com.hazymoonstudio.legaldecision.domain.util.DomainMapper
import com.hazymoonstudio.legaldecision.utils.ComponentType

class ComponentDtoMapper : DomainMapper<ComponentDto, Component> {
    override fun mapToDomainModel(model: ComponentDto): Component {
        return Component(
            type = ComponentType.create(model.type),
            text = model.text,
            subText = model.subText,
        )
    }

    override fun mapFromDomainModel(domainModel: Component): ComponentDto {
        return ComponentDto(
            type = domainModel.type.id,
            text = domainModel.text,
            subText = domainModel.subText,

        )
    }

    fun toDomainList(initial: List<ComponentDto>): List<Component>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Component>): List<ComponentDto>{
        return initial.map { mapFromDomainModel(it) }
    }
}