package maxiso.datalayer.merch;
import java.util.*;

public interface IMerchImporter {
    List<Merch> loadFigurines();
    List<Merch> loadPlushies();
    List<Merch> loadElectronics();
    List<Merch> loadAllMerch();
}
