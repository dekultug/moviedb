package com.example.themoviedatabase.common.event

import ai.ftech.base.eventbus.IEvent

class CreateListSuccess() : IEvent

class RateMovieSuccess(val type: String) : IEvent

