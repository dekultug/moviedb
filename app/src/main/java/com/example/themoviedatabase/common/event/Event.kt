package com.example.themoviedatabase.common.event

import ai.ftech.base.eventbus.IEvent

class CreateListSuccess() : IEvent

class CreateListSuccessVersion2() : IEvent

class RateMovieSuccess(val type: String) : IEvent

class AddToListSuccess(): IEvent

