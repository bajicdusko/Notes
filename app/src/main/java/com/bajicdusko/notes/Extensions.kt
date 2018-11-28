package com.bajicdusko.notes

import org.ocpsoft.prettytime.PrettyTime
import java.util.*

/**
 * Created by Dusko Bajic on 25.09.18.
 * GitHub @bajicdusko
 */

fun Date.prettyTime(): String = PrettyTime().format(this)