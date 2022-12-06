
package crouskiebackoffice.model;

import java.util.List;


public interface AttachImage {
    public void attachPicture(Picture pic);
    
    public List<Picture> getPictures();
    
    public boolean isSingleAttach();
}
