package cn.tedu.cloud_note.entity;

// ’≤ÿ
public class CollectionNote {
	private String cn_collection_id;
	private String cn_collection_user_id;
	private String cn_collection_share_id;
	public String getCn_collection_id() {
		return cn_collection_id;
	}
	public void setCn_collection_id(String cn_collection_id) {
		this.cn_collection_id = cn_collection_id;
	}
	public String getCn_collection_user_id() {
		return cn_collection_user_id;
	}
	public void setCn_collection_user_id(String cn_collection_user_id) {
		this.cn_collection_user_id = cn_collection_user_id;
	}
	public String getCn_collection_share_id() {
		return cn_collection_share_id;
	}
	public void setCn_collection_share_id(String cn_collection_share_id) {
		this.cn_collection_share_id = cn_collection_share_id;
	}
	@Override
	public String toString() {
		return "Collection [cn_collection_id=" + cn_collection_id + ", cn_collection_user_id=" + cn_collection_user_id
				+ ", cn_collection_share_id=" + cn_collection_share_id + "]";
	}
	
	
}
