package src;

import Repository.MethodRepository;
import ViewModel.ViewModel;
import com.google.common.eventbus.EventBus;

public enum Configuration {
    instance;

    public final String userDirectory = System.getProperty("user.dir");

    public EventBus eventBus = new EventBus("ECB-"+1);

    public ViewModel viewModel = new ViewModel(1,eventBus);


    public MethodRepository methodRepository = new MethodRepository();


}
