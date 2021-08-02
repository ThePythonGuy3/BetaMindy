package betamindy.content;

import betamindy.type.*;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.type.*;

public class ShopItems implements ContentList {
    public ShopItem bundle1, bundle2;

    @Override
    public void load(){
        bundle1 = new ShopItem("entry-bundle", 250){{
            bundleItems = ItemStack.with(Items.copper, 100, Items.lead, 100);
        }};

        bundle2 = new ShopItem("boost-bundle", 1850){{
            bundleItems = ItemStack.with(Items.silicon, 1000, Items.graphite, 1000);
        }};
    }
}
