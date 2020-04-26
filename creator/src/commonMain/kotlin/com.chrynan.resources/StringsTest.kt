@file:String(name = "test", value = "Testing", locale = "en")
@file:String(name = "", value = "")

@file:StringGroup(
    name = "test", values = [
        StringValue(value = "", locale = ""),
        StringValue("")
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