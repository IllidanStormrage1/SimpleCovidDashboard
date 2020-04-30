package ru.zkv.covid19app.presentation.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.zkv.covid19app.domain.interactor.MainInteractor
import ru.zkv.covid19app.presentation.view.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(mainInteractor: MainInteractor) : MvpPresenter<MainView>() {


}