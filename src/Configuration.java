package src;

import Mediator.Mediator;
import ViewModel.ViewModel;
import com.google.common.eventbus.EventBus;

import java.util.ArrayList;

public enum Configuration {
    instance;

    public final String userDirectory = System.getProperty("user.dir");

    public EventBus eventBus = new EventBus("ECB-"+1);

    public ViewModel viewModel = new ViewModel(1,eventBus);


}
