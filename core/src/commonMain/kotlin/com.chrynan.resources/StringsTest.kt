@file:String(name = "test", value = "Testing", locale = "en")
@file:String(name = "", value = "")

@file:StringGroup(
    name = "test", values = [
        StringGroupItem(value = "", locale = ""),
        StringGroupItem("")
    ]
)


@file:String(
    name = "", value = """
    jhgf
    hjgf\\
    
    fjgh
    
    jhfg
    
    """
)

package com.chrynan.resources