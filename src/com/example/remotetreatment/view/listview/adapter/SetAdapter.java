package com.example.remotetreatment.view.listview.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class SetAdapter<T> extends BaseAdapter implements Iterable<T> {
	private Context mContext;
	private LinkedHashSet<T> mHelperSet;
	private final Object mLock;
	private boolean mNotifyOnChange;
	private List<T> mObjects;
	private ArrayList<T> mOriginalValues;

	public SetAdapter(Context paramContext) {
		this.mHelperSet = new LinkedHashSet<T>();
		this.mLock = new Object();
		this.mNotifyOnChange = true;
		this.mContext = paramContext;
		this.mObjects = new ArrayList<T>();
	}

	public SetAdapter(Context paramContext, Collection<T> paramCollection) {
		this(paramContext);
		this.mObjects.addAll(paramCollection);
	}

	public SetAdapter(Context paramContext, T[] paramArrayOfT) {
		this(paramContext);
		this.mObjects.addAll(Arrays.asList(paramArrayOfT));
	}

	public void add(T paramT) {
		if (this.mOriginalValues != null)
			synchronized (this.mLock) {
				if (!(this.mOriginalValues.contains(paramT))) {
					this.mOriginalValues.add(paramT);
				}
			}
		if (!this.mObjects.contains(paramT)) {
			this.mObjects.add(paramT);
		}
		notifyDataSetChangedDefault();
	}

	public void addAll(Collection<? extends T> paramCollection) {
		if (this.mOriginalValues != null)
			synchronized (this.mLock) {
				addAllToArray(this.mOriginalValues, paramCollection);
			}
		addAllToArray(this.mObjects, paramCollection);
		this.mOriginalValues = new ArrayList<T>(this.mObjects);
		notifyDataSetChangedDefault();
	}

	public void addAll(int paramInt, Collection<? extends T> paramCollection) {
		if (this.mOriginalValues != null)
			synchronized (this.mLock) {
				insertAllToArray(this.mOriginalValues, paramInt, paramCollection);
			}
		insertAllToArray(this.mObjects, paramInt, paramCollection);
		notifyDataSetChangedDefault();
	}

	public void addAll(int paramInt, T[] paramArrayOfT) {
		if (paramArrayOfT != null && paramArrayOfT.length > 0)
			addAll(paramInt, Arrays.asList(paramArrayOfT));
	}

	public void addAll(T[] paramArrayOfT) {
		if (paramArrayOfT != null && paramArrayOfT.length > 0)
			addAll(Arrays.asList(paramArrayOfT));
	}

	private void addAllToArray(List<T> paramList, Collection<? extends T> paramCollection) {
		this.mHelperSet.clear();
		this.mHelperSet.addAll(paramList);
		this.mHelperSet.addAll(paramCollection);
		paramList.clear();
		paramList.addAll(this.mHelperSet);
	}

	public void clear() {
		if (this.mOriginalValues != null) {
			synchronized (this.mLock) {
				this.mOriginalValues.clear();
			}
		}
		this.mObjects.clear();
		notifyDataSetChangedDefault();
	}

	public Context getContext() {
		return this.mContext;
	}

	public int getCount() {
		return this.mObjects.size();
	}

	public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		return getView(paramInt, paramView, paramViewGroup);
	}

	public T getItem(int paramInt) {
		return this.mObjects.get(paramInt);
	}

	public final String LOCK = "LOCK";

	public void setItem(int paramInt, T object) {
		synchronized (LOCK) {
			this.mObjects.set(paramInt, object);
		}
	}

	public long getItemId(int paramInt) {
		return paramInt;
	}

	public List<T> getList() {
		ArrayList<T> list = new ArrayList<T>(mObjects);
		return list;
	}

	public int getPosition(T paramT) {
		return this.mObjects.indexOf(paramT);
	}

	public abstract View getView(int paramInt, View paramView, ViewGroup paramViewGroup);

	public void insert(T paramT, int paramInt) {
		if (this.mOriginalValues != null)
			synchronized (this.mLock) {
				if (!(this.mOriginalValues.contains(paramT))) {
					this.mOriginalValues.add(paramInt, paramT);
					if (this.mNotifyOnChange)
						super.notifyDataSetChanged();
				}
				return;
			}
		if (this.mObjects.contains(paramT))
			return;
		this.mObjects.add(paramInt, paramT);
		if (!(this.mNotifyOnChange))
			return;
		super.notifyDataSetChanged();
	}

	private void insertAllToArray(List<T> originList, int paramInt, Collection<? extends T> paramCollection) {
		if (paramInt < 0 || paramInt > originList.size()) {
			throw new IndexOutOfBoundsException();
		}

		if (paramInt == originList.size()) {
			addAllToArray(originList, paramCollection);
			return;
		}
		this.mHelperSet.clear();
		if (paramInt == 0) {
			this.mHelperSet.addAll(paramCollection);
			this.mHelperSet.addAll(originList);
		} else {
			this.mHelperSet.addAll(originList.subList(0, paramInt));
			this.mHelperSet.addAll(paramCollection);
			this.mHelperSet.addAll(originList.subList(paramInt, originList.size()));
		}
		originList.clear();
		originList.addAll(this.mHelperSet);
	}

	public Iterator<T> iterator() {
		return this.mObjects.iterator();
	}

	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		this.mNotifyOnChange = true;
	}

	public void notifyDataSetChangedDefault() {
		if (this.mNotifyOnChange) {
			super.notifyDataSetChanged();
		}
	}

	public void remove(int paramInt) {
		if (this.mOriginalValues != null) {
			synchronized (this.mLock) {
				this.mOriginalValues.remove(paramInt);
				this.mObjects.remove(paramInt);
				if (this.mNotifyOnChange) {
					super.notifyDataSetChanged();
				}
				return;
			}
		}
	}

	public void remove(T paramT) {
		if (this.mOriginalValues != null) {
			synchronized (this.mLock) {
				this.mOriginalValues.remove(paramT);
				notifyDataSetChangedDefault();
				return;
			}
		}
		this.mObjects.remove(paramT);
		notifyDataSetChangedDefault();
	}

	public void removeAll(Collection<T> paramCollection) {
		if (this.mOriginalValues != null)
			synchronized (this.mLock) {
				this.mOriginalValues.removeAll(paramCollection);
				notifyDataSetChangedDefault();
				return;
			}
		this.mObjects.removeAll(paramCollection);
		notifyDataSetChangedDefault();
	}

	public void removeAll(T[] paramArrayOfT) {
		if (paramArrayOfT != null && paramArrayOfT.length > 0)
			removeAll(Arrays.asList(paramArrayOfT));
	}

	public void replace(int paramInt, T paramT) {
		if (this.mOriginalValues != null)
			synchronized (this.mLock) {
				if (!(this.mOriginalValues.contains(paramT))) {
					this.mOriginalValues.set(paramInt, paramT);
					notifyDataSetChangedDefault();
				}
			}
		if (!this.mObjects.contains(paramT))
			this.mObjects.set(paramInt, paramT);
		notifyDataSetChangedDefault();
	}

	public void retainAll(Collection<T> paramCollection) {
		synchronized (this.mLock) {
			if (this.mOriginalValues != null) {
				this.mOriginalValues.retainAll(paramCollection);
			} else {
				this.mObjects.retainAll(paramCollection);
			}
		}
		notifyDataSetChangedDefault();
	}

	public void retainAll(T[] paramArrayOfT) {
		if (paramArrayOfT == null || paramArrayOfT.length <= 0)
			return;
		retainAll(Arrays.asList(paramArrayOfT));
	}

	public void setNotifyOnChange(boolean paramBoolean) {
		this.mNotifyOnChange = paramBoolean;
	}

	public void sort(Comparator<? super T> paramComparator) {
		Collections.sort(this.mObjects, paramComparator);
	}
}