package com.hazymoonstudio.legaldecision.domain.model

import com.hazymoonstudio.legaldecision.utils.ComponentType

data class Component(var type: ComponentType = ComponentType.TEXT, var text: String = "", var date: String = "", var time: String = "")
