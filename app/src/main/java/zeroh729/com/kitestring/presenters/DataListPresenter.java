package zeroh729.com.kitestring.presenters;

import zeroh729.com.kitestring.interactors.DataListSystem;
import zeroh729.com.kitestring.presenters.base.BasePresenter;

public class DataListPresenter implements BasePresenter {
    private Screen screen;
    private System system;

    public DataListPresenter(Screen screen) {
        this.screen = screen;
        system = new DataListSystem();
    }

    @Override
    public void setup() {

    }

    @Override
    public void updateState() {

    }

    @Override
    public void setState(int state) {

    }

    public interface Screen {

    }

    public interface System {

    }
}
