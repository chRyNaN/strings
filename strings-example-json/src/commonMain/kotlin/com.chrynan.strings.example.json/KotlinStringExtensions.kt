        package com.chrynan.strings.example.json
        
        import com.chrynan.strings.core.ResourceID
        import com.chrynan.strings.core.StaticStringResourceID
        import com.chrynan.strings.core.DynamicStringResourceID
        import com.chrynan.strings.core.HtmlStringResourceID
        import com.chrynan.strings.core.PluralStringResourceID
        import com.chrynan.strings.core.StringArrayResourceID
        
        import com.chrynan.strings.accessor.Strings
        import com.chrynan.strings.core.Locale
        import com.chrynan.strings.core.Quantity
        
        inline fun Strings.helloWorld(locale: String = Locale.default): String =
Strings.getStaticString(resourceID = StringResID.helloWorld, locale = locale)