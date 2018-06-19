package ru.app.rz0.vyngtestapp.models.search;

import android.os.Parcel;
import android.os.Parcelable;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class VideoItem implements Parcelable {

  @Id
  public long id;

  public String key;
  public String imageUrl;
  public String videoUrl;
  public int thumbUpCount;
  public int thumbDownCount;

  public VideoItem() {
  }

  public VideoItem(String key, String imageUrl, String videoUrl) {
    this.key = key;
    this.imageUrl = imageUrl;
    this.videoUrl = videoUrl;
  }

  protected VideoItem(Parcel in) {
    key = in.readString();
    imageUrl = in.readString();
    videoUrl = in.readString();
    thumbUpCount = in.readInt();
    thumbDownCount = in.readInt();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(key);
    dest.writeString(imageUrl);
    dest.writeString(videoUrl);
    dest.writeInt(thumbUpCount);
    dest.writeInt(thumbDownCount);
  }

  @SuppressWarnings("unused")
  public static final Parcelable.Creator<VideoItem> CREATOR = new Parcelable.Creator<VideoItem>() {
    @Override
    public VideoItem createFromParcel(Parcel in) {
      return new VideoItem(in);
    }

    @Override
    public VideoItem[] newArray(int size) {
      return new VideoItem[size];
    }
  };
}