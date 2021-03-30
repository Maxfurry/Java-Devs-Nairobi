package com.andela.javadevnai.adapter;

import org.junit.Test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andela.javadevnai.R;
import com.andela.javadevnai.model.JavaGithubNai;
import com.andela.javadevnai.view.MyViewHolder;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)

@PrepareForTest({LayoutInflater.class})
public class GithubAdapterTest {
    private int dummyTestId;
    private GithubAdapter adapter;

    Context mockContext;
    List<JavaGithubNai> devList;

    @Mock
    LayoutInflater mockInflater;

    @Mock
    View mockView;

    @Mock
    ViewGroup mockParent;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        dummyTestId = R.string.dummyTestId;
        mockStatic(LayoutInflater.class);

        adapter = new GithubAdapter(mockContext, devList);
    }

    @Test
    public void onCreateViewHolder() {
        when(mockParent.getContext()).thenReturn(mockContext);
        when(LayoutInflater.from(mockContext)).thenReturn(mockInflater);
        when(mockInflater.inflate(anyInt(), eq(mockParent), eq(false))).thenReturn(mockView);
        MyViewHolder viewHolder = adapter.onCreateViewHolder(mockParent, dummyTestId);
        assertNotNull(viewHolder);
        assertEquals(viewHolder.itemView, mockView);
    }

}