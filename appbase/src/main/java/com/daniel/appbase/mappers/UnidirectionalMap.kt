package com.daniel.appbase.mappers

/**
 * Created by Vincent on 2/21/19
 */
interface UnidirectionalMap<F, T> {

  fun map(item: F): T
}