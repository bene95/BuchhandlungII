package src;

import View.Buchhandlung;
import ViewModel.ViewModel;
import com.google.common.eventbus.EventBus;

public enum Configuration {
    instance;

    public final String userDirectory = System.getProperty("user.dir");

    public EventBus eventBus = new EventBus("ECB-"+1);
    public Buchhandlung buchhandlung = new Buchhandlung();
    public ViewModel viewModel = new ViewModel(1,eventBus,buchhandlung);
    public String fileSeparator = System.getProperty("file.separator");


    //public MethodRepository methodRepository = new MethodRepository();


}
