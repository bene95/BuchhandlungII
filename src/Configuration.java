package src;

import Mediator.Mediator;
import ViewModel.ViewModel;

public enum Configuration {
    instance;

    public final String userDirectory = System.getProperty("user.dir");
    public ViewModel viewModel = new ViewModel(1,new Mediator(1));


}
