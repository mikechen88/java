package foolstudio.demo;

public class FileInfo {
	private boolean isFolder;
	private String name;
	
	public FileInfo(String _name, boolean _isFolder) {
		setName(_name);
		setFolder(_isFolder);
	}
	
	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}
	public boolean isFolder() {
		return isFolder;
	}
	public void setName(String name) {
		this.name = name;
	}

	//必须重载toString方法
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (getName() );
		//return super.toString();
	}

	public String getName() {
		return name;
	}
};
